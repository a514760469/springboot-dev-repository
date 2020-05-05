package com.gds.springboot.rabbitmq.rabbit.rpc;

/**
 * @author zhanglifeng
 * @date 2019/12/3/0003
 */
public class SendSMSHandler {

    public SendStatus handleMessage(byte[] body) {
        String _body = new String(body);
        System.out.println("handleMessage: " + _body);
        String[] sms = _body.split(":");
        String phone = sms[0];
        String content = sms[1];

        boolean is = SendSMSTool.sendSMS(phone, content);
        SendStatus sendStatus = new SendStatus();
        sendStatus.setPhone(phone);
        sendStatus.setResult(is ? "true" : "false");
//        try {
//            TimeUnit.SECONDS.sleep(6);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        return sendStatus;
    }


    private static class SendSMSTool {
        public static boolean sendSMS(String phone, String content) {
            System.out.println("发送短信内容：【" + content + "】到手机号：" + phone);
            return phone.length() > 6;
        }
    }
}
