The Solr search part.

Search with Highlight function:

http://192.168.71.151:8080/solr/select/?q=book_title:Edition&hl=true&hl.fl=book_title

if we don't add hl.fl part, we only get the result with ID.