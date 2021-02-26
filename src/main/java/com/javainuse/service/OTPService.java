package com.javainuse.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class OTPService {
    //Assigning of the time expiration of the OTP
    private static final Integer EXPIRE_MINS = 5;
    //OTP will be stored in the local system cache
    private LoadingCache<String, Integer> otpCache;
    public OTPService(){
        super();
        otpCache = CacheBuilder.newBuilder().
                expireAfterWrite(EXPIRE_MINS, TimeUnit.MINUTES)
                .build(new CacheLoader<String, Integer>() {
                    public Integer load(String key) {
                        return 0;
                    }
                });
    }

    //This method is to generate random numbers which will be treated as OTP
    //Here key is the username
    public int generateOTP(String key){
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        otpCache.put(key, otp);
        return otp;
    }

    //This method is to return OTP from the local system cache
    public int getOtp(String key){
        try{
            return otpCache.get(key);
        }catch (Exception e){
            return 0;
        }
    }

    //For respective user,the OTP is deleted which is already saved as cache
    public void clearOTP(String key){
        otpCache.invalidate(key);
    }
}
