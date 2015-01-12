package com.google.gwt.debugformat.client;

import java.util.Map;

/**
 * Provides a custom format for subclasses of Map that just shows their keys and values.
 */
class MapMirror extends Mirror {

  @Override
  public boolean canDisplay(Any any) {
    return any.toObject() instanceof Map;
  }

  @Override
  public boolean hasBody(Any any) {
    Map m = (Map) any.toObject();
    assert m != null;
    return !m.isEmpty();
  }

  @Override
  public Page getBody(Any any) {
    Map m = (Map) any.toObject();

    Children out = Children.create();
    out.addInt("size", m.size());
    out.add("entries", makeEntries(m));
    return out.firstPage();
  }

  private static Page makeEntries(Map map) {
    // It would be nice to sort the list, but we can only do that if the keys are comparable.
    // (And we don't want to sort a LinkedHashMap.)

    Children out = Children.create();

    for (Object o : map.entrySet()) {
      Map.Entry e = (Map.Entry) o;
      Object key = e.getKey();
      String keyName = key == null ? "null" : key.toString();
      out.add(keyName, e.getValue());
    }

    return new Page(out, 0);
  }
}
