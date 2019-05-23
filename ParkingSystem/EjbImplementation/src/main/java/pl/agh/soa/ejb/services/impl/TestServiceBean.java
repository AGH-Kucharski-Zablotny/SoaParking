package pl.agh.soa.ejb.services.impl;

import pl.agh.soa.ejb.services.local.TestServiceLocal;
import pl.agh.soa.ejb.services.remote.TestServiceRemote;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

@Local(TestServiceLocal.class)
@Remote(TestServiceRemote.class)
@Stateless
public class TestServiceBean implements TestServiceLocal, TestServiceRemote {
    public String ping() {
        return "pong";
    }
}
