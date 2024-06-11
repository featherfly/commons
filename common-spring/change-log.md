# 0.4.2 2024-06-07
1. 修复没有移除RestartClassLoaderClassDefiner在Service loader里的定义

# 0.4.1 2024-06-04
1. 修复错误的依赖版本
2. 移除RestartClassLoaderClassDefiner类，SmartClassLoaderClassDefiner已经能够代替了

# 0.4.0 2024-05-16

1. 加入SmartClassLoaderClassDefiner

# 0.3.0 2022-07-15

1. 加入RestartClassLoaderClassDefiner

# 0.2.2 2021-12-20

1. 修复MulitiCacheManager，MulitiUniqueKeyCacheManager decorateCache(Cache)把包装好的cache再包装的问题
2. 升级依赖
   

# 0.2.1 2019-12-03

1. 修改group名称
2. 升级spring依赖

# 0.2.0 2019-8-27

1. 把ReadWriteDataSource从单独模块移植到当前包


# 0.1.0 2019-8-20

1. 从common和web-spring移植过来