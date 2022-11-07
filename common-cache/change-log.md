# 0.3.0 2022-11-07
1. 加入ExpiryPolicys
2. CacheConfig参数ttl,maxIdleTime变更为expiry,expiryPolicy
3. 删除ExpiryPolicyImpl

# 0.2.0 2022-11-07
1. CacheConfig构造函数重构

# 0.1.0 2020-11-14
1. 实现CacheManager(Map<String, CacheConfig> cacheConfigs),通过传入的配置加载缓存
  