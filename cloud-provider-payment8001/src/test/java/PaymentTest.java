import com.atguigu.springcloud.dao.PaymentDao;
import com.atguigu.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.annotation.Resource;

@Slf4j
public class PaymentTest {
    @Resource
    private PaymentDao paymentDao;

    @Test
    public void testGet(){
//        log.info("开始查询数据");
//        log.info(paymentDao.toString());
//        Payment payment = paymentDao.getPaymentById(1);
//        System.out.println("========"+payment.getSerial());
    }
}
