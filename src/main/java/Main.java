import com.hawk.commentcenter.service.GreetService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author junxiong.lang
 * @date 2016/11/8 16:08
 */
public class Main {
    private static ClassPathXmlApplicationContext ctx;
    private static GreetService greetService;

    static {
        ctx = new ClassPathXmlApplicationContext("spring.xml");
        ctx.start();

        greetService = ctx.getBean(GreetService.class);
    }

    public static void main(String[] args) throws Exception {
        greetService.sayAll();
    }
}