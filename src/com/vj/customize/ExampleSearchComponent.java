package com.vj.customize;

import java.io.IOException;

import org.apache.solr.handler.component.ResponseBuilder;
import org.apache.solr.handler.component.SearchComponent;
import org.apache.solr.request.UnInvertedField;
import org.apache.solr.search.SolrIndexSearcher;

public class ExampleSearchComponent extends SearchComponent {

  String[] fieldNames = null;

  @Override
  public String getDescription() {
    // TODO Auto-generated method stub
    return "ExampleSearchComponent";
  }

  @Override
  public String getSource() {
    // TODO Auto-generated method stub
    return "ExampleSearchComponent.Source";
  }

  @Override
  public String getSourceId() {
    // TODO Auto-generated method stub
    return "ExampleSearchComponent.SourceID";
  }

  @Override
  public String getVersion() {
    // TODO Auto-generated method stub
    return "ExampleSearchComponent.version.1.0";
  }

  @Override
  public void prepare(ResponseBuilder builder) throws IOException {
    // TODO Auto-generated method stub
    fieldNames = builder.req.getParams().get("exampleFields", "").split(",");
  }

  @Override
  public void process(ResponseBuilder builder) throws IOException {
    // TODO Auto-generated method stub
    if (fieldNames != null) {
      long totalMemorySize = 0;
      SolrIndexSearcher searcher = builder.req.getSearcher();
      for (String fieldName : fieldNames) {
        UnInvertedField field = UnInvertedField.getUnInvertedField(fieldName, searcher);
        totalMemorySize += field.memSize();        
      }
      builder.rsp.add("example", totalMemorySize);
    }
  }

}
/*
modify solrconfig.xml to add the Handler:
<requestHandler name="/example" class="solr.SearchHandler">
  <arr name="components">
    <str>exampleComponent</str>
  </arr>
</requestHandler>
<searchComponent name="exampleComponent" class="com.vj.customize.ExampleSearchComponent">
</searchComponent>

run following to test query:
http://localhost/solr/example?q=*:*&exampleFields=cat,name,popularity


 */ 
