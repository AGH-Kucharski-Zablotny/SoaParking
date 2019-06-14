package pl.agh.soa.ejb.services.impl;

import pl.agh.soa.dao.UsersDAO;
import pl.agh.soa.dto.UserData;
import pl.agh.soa.ejb.services.ApplicationManager;
import pl.agh.soa.ejb.services.remote.AccountManagerRemote;

import javax.annotation.PostConstruct;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.ejb.*;
import javax.persistence.NoResultException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Remote(AccountManagerRemote.class)
@Singleton
@Startup
public class AccountManagerBean implements AccountManagerRemote {

    @EJB(lookup = "java:global/ApplicationRouter-1.0/ApplicationManagerBean!pl.agh.soa.ejb.services.ApplicationManager")
    private ApplicationManager applicationManager;

    private static String key = "secretKey";
    private static String salt = "soaislove";

    @PostConstruct
    public void init() {
        applicationManager.registerApplication(ApplicationManager.Application.ACCOUNT_MANAGER, "http://localhost:8080/EjbAccountImpl-1.0/accounts");
    }

    @Override
    public UserData getUser(Integer id) {
        try {
            return UsersDAO.getInstance().getItem(id);
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public UserData getUserByUsernamePassword(String username, String password)
    {
        try
        {
            String encryptedPassword = encryptMD5(password);
            return UsersDAO.getInstance().getUserByLoginPassword(username, encryptedPassword);
        }
        catch (NoResultException | NoSuchAlgorithmException e)
        {
            return null;
        }
    }

    @Override
    public void updateUser(UserData user)
    {
        try {
            user.setPassword(encryptMD5(user.getPassword()));
            UsersDAO.getInstance().updateItem(user);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createUser(UserData user)
    {
        try {
            user.setPassword(encryptMD5(user.getPassword()));
            UsersDAO.getInstance().addItem(user);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private String encryptMD5(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] passwordBytes = password.getBytes();
        byte[] hash = md.digest(passwordBytes);
        return new String(Base64.getEncoder().encode(hash));
    }

    @Override
    public List<UserData> getAttendants() {
        return UsersDAO.getInstance().getItems().stream().filter(u -> u.getRegion() != null).collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Integer id) {
        UsersDAO.getInstance().deleteItem(id);
    }

    public UserData getAttendantForRegion(Integer regionId) {
        return UsersDAO.getInstance().getAttendantForRegion(regionId);
    }

    @Override
    public UserData getUserByLogin(String login) {
        return UsersDAO.getInstance().getUserByLogin(login);
    }

    private String encrypt(String toEncrypt, String key)
    {
        try
        {
            byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(key.toCharArray(), salt.getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
            return Base64.getEncoder().encodeToString(cipher.doFinal(toEncrypt.getBytes("UTF-8")));
        }
        catch (Exception e)
        {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }
}
