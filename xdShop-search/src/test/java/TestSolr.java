import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by pro on 16/12/25.
 */
public class TestSolr {

    @Test
    public void testAdd(){
        SolrServer solrServer=new HttpSolrServer("http://192.168.1.104:8080/solr");
        SolrInputDocument document=new SolrInputDocument();
//        System.out.println(solrServer);
        document.addField("id","test1");
        document.addField("item_title","测试商品");
        try {
            solrServer.add(document);
            solrServer.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testDelete(){
        SolrServer solrServer=new HttpSolrServer("http://192.168.1.104:8080/solr");
        SolrInputDocument document=new SolrInputDocument();
        try {
            solrServer.deleteById("test1");
            solrServer.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
