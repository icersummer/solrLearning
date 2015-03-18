package com.vj.search;

/*
assume we have a snippet in solrconfig.xml:

  <requestHandler name="standard" class="solr.SearchHandler" default="true">
    <!-- default values for query parameters -->
     <lst name="defaults">
        <str name="echoParams">explicit</str>

        <int name="rows">4000</int>
        <str name="hl">true</str>
        <str name="fl">id,score,name,contentType,keywords_en</str>
        <str name="hl.fl">keywords_en</str>
        <str name="hl.alternateField">some field</str>
        <str name="hl.usePhraseHighlighter">true</str>
        <str name="hl.mergeContiguous">false</str>
        <str name="hl.fragmenter">regex</str>
        <str name="hl.simple.pre"><![CDATA[<b>]]></str>
        <str name="hl.simple.post"><![CDATA[</b>]]></str>
        <int name="hl.maxAnalyzedChars">512000</int>
        <int name="hl.snippets">10</int>
        <int name="hl.maxAlternateFieldLength">200</int>
        <str name="spellcheck">true</str>
        <int name="spellcheck.count">1</int>
     </lst>
     <arr name="last-components">
        <str>spellcheck</str>
     </arr>
  </requestHandler>
To confirm what all these params for ?
 */
/**
 * The class refer to {@link com.org.solr.SolrCore SolrCore} a good file.
 * <p>
 * Rose abc -> Role xyz.
 * use the <code>HashMapVJ</code> constructor.
 * <p>
 * <b>Supported API: </b>true
 */
public class VerifyParamsInProject {

}
