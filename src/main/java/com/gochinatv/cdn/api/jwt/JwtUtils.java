package com.gochinatv.cdn.api.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.crypto.MacProvider;

import javax.xml.bind.DatatypeConverter;
import java.security.Key;

/**
 * ${DESCRIPTION}
 *
 * @auhtor jacktomcat
 * @create 2018-01-29 下午10:58
 */
public class JwtUtils {


    static Key key = MacProvider.generateKey();//这里是加密解密的key。
    //static String key = "123456";

    static String compactJws = Jwts.builder()//返回的字符串便是我们的jwt串了
            .setHeaderParam("typ", "JWT")
            .setSubject("Joe")//设置主题
            //.signWith(SignatureAlgorithm.HS512, key)//设置算法（必须）
            .signWith(SignatureAlgorithm.HS512, key)
            .compact();//这个是全部设置完成后拼成jwt串的方法
//eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJKb2UifQ.kUgxM8ZdluzTnCXhrr7xPrVX7C9B2XdvREmIxyez4yqCs38wis918kfQ9rYxDF7Cz3Q6TgQUhNvP_FYbeUkSFQ


    public static void main(String[] args) {
        System.out.println(compactJws);
        decode();
    }



    public static void decode(){

        try {

            Jws<Claims> parseClaimsJws = Jwts.parser().setSigningKey(key).parseClaimsJws(compactJws);//compactJws为jwt字符串
            Claims body = parseClaimsJws.getBody();//得到body后我们可以从body中获取我们需要的信息
            //比如 获取主题,当然，这是我们在生成jwt字符串的时候就已经存进来的
            String subject = body.getSubject();
            JwsHeader header = parseClaimsJws.getHeader();

            System.out.println(compactJws);
            System.out.println(body);
            System.out.println(subject);
            System.out.println(header);

            //OK, we can trust this JWT

        }catch(SignatureException e){

        } catch (MalformedJwtException e) {
            // TODO: handle exception
            // don't trust the JWT!
            // jwt 解析错误
        } catch (ExpiredJwtException e) {
            // TODO: handle exception
            // jwt 已经过期，在设置jwt的时候如果设置了过期时间，这里会自动判断jwt是否已经过期，如果过期则会抛出这个异常，我们可以抓住这个异常并作相关处理。
        }

    }

}
