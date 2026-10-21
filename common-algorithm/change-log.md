# 1.2.0 2026-10-21

feat:
  1. MD5,SHA,SM3 add method `byte[] encrypt(InputStream is, int chunkSize)` 

# 1.1.0 2020-4-17

feat:
  1. 加入国密sm2,sm3,sm4
  2. 把Base64,MD5,SHA等算法之前的encode decode方法名统一改为encrypt,decrypt方法名
    

# 1.0.0 2020-3-22

feat:
  1. 从featherfly-algorithm迁移过来
  2. 加入HMACMD5和HMACSHA