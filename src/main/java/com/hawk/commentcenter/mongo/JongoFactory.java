package com.hawk.commentcenter.mongo;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import org.jongo.Jongo;
import org.springframework.beans.factory.FactoryBean;

/**
 * @author junxiong.lang
 * @date 2016/12/7 14:31
 */
public class JongoFactory implements FactoryBean<Jongo> {
    private String host;
    private String port;
    private String dbName;
    private String user;
    private String pass;

    public JongoFactory() {
    }

    public JongoFactory(String host, String port, String dbName, String user, String pass) {
        this.host = host;
        this.port = port;
        this.dbName = dbName;
        this.user = user;
        this.pass = pass;
    }

    @Override
    public Jongo getObject() throws Exception {
        ServerAddress serverAddress = new ServerAddress(host, Integer.parseInt(port));
        MongoClient client = new MongoClient(serverAddress);
        DB db = client.getDB(dbName);
        Jongo jongo = new Jongo(db);

        return jongo;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
