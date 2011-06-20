package org.gedcomx.record;

import org.codehaus.jackson.node.ObjectNode;
import org.gedcomx.types.RelationshipType;
import org.testng.annotations.Test;
import org.w3c.dom.Document;

import javax.xml.namespace.QName;

import static org.gedcomx.rt.SerializationUtil.toJsonNode;
import static org.gedcomx.rt.SerializationUtil.toXmlDom;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertNull;

/**
 * @author Ryan Heaton
 */
@Test
public class TestRelationship {

  public void testSerializationOfRelationshipType() throws Exception {
    CoupleRelationship cr = new CoupleRelationship();
    cr.setId("id");
    Document dom = toXmlDom(cr);
    assertNull(dom.getDocumentElement().getAttributeNode("type"));
    assertEquals(0, dom.getDocumentElement().getElementsByTagName("type").getLength());

    ObjectNode node = toJsonNode(cr);
    assertNull(node.get("type"));
    
    OtherRelationship or = new OtherRelationship();
    or.setId("id");
    or.setType(new QName("urn:custom", "custom"));
    dom = toXmlDom(or);
    assertNotNull(dom.getDocumentElement().getAttributeNode("type"));
    assertEquals(0, dom.getDocumentElement().getElementsByTagName("type").getLength());

    node = toJsonNode(or);
    assertNotNull(node.get("type"));
  }
}
