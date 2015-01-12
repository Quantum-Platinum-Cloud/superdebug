package com.google.gwt.debugformat.client;

/**
 * A reflective representation of a Java object.
 */
class JavaObject extends Any {
  protected JavaObject() {}

  final String getClassName() {
    return getClass().getName();
  }

  final native boolean hasFields() /*-{
    return !!Object.keys(this).length
  }-*/;

  final native Mirror.Children getFields() /*-{

    // TODO: we need a test to ensure this is in sync with JsIncrementalNamer
    function getFieldName(key) {
      if (!key.endsWith("_g$")) {
        return key;
      }
      var len = key.lastIndexOf('_', key.length - 4);
      if (len < 1) {
        return key;
      }
      return key.substring(0, len);
    }

    var keys = Object.keys(this);
    var fields = [];
    for (var i = 0; i < keys.length; ++i) {
      var key = keys[i];
      var field = {};
      field.name = getFieldName(key);
      field.value = this[key];
      fields.push(field);
    }
    fields.sort(function compare(a, b) {
      if (a.name < b.name) {
        return -1;
      } else if (a.name == b.name) {
        return 0;
      } else {
        return 1;
      }
    });
    return fields;
  }-*/;
}
