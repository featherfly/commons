package cn.featherfly.common.server;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartRequest;

@RestController("/")
public class HelloController {

    private void showRequest(HttpServletRequest request) {
        Enumeration<String> enums = request.getHeaderNames();
        System.out.println("headers");
        while (enums.hasMoreElements()) {
            String head = enums.nextElement();
            System.out.println("  " + head + "\n\t " + request.getHeader(head));
        }
    }

    @RequestMapping("/hello")
    public String index() {
        return "hello world";
    }

    @RequestMapping(path = "/upload", method = { RequestMethod.POST })
    public String upload(@RequestParam(name = "key") String key, MultipartRequest multipartRequest,
            HttpServletRequest request) {
        showRequest(request);
        Iterator<String> it = multipartRequest.getFileNames();
        System.out.println("key = " + key);
        System.out.println("multipartRequest params ");
        while (it.hasNext()) {
            String na = it.next();
            System.out.println("  name = " + na);
            System.out.println("  name = " + multipartRequest.getFile(na).getName());
            System.out.println("  filename = " + multipartRequest.getFile(na).getOriginalFilename());
            try {
                System.out.println("  decode(filename) = "
                        + URLDecoder.decode(multipartRequest.getFile(na).getOriginalFilename(), "UTF-8"));
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
            System.out.println("  contenttype = " + multipartRequest.getFile(na).getContentType());
            try {
                System.out.println("  file value = "
                        + new String(multipartRequest.getFile(na).getBytes(), StandardCharsets.UTF_8));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "OK";
    }

    @RequestMapping(path = "/user", method = { RequestMethod.GET })
    public User user() {
        User user = new User();
        user.setName("yufei羽飞");
        user.setAge(18);
        return user;
    }

    @RequestMapping(path = "/user", method = { RequestMethod.POST })
    public String adduser(@RequestBody User user, HttpServletRequest request) {
        showRequest(request);
        System.out.println(user);
        return "OK";
    }
}