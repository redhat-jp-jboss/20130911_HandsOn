importPackage(org.infinispan.client.hotrod)
var mgr = new RemoteCacheManager("localhost:11222")
var cache = mgr.getCache("expirationCache")

