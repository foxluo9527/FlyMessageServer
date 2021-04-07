package com.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class SMSUtil {
    public static ArrayList<SMSBean> SMSList = new ArrayList<>();

    /**
     * 发送指定模板短信
     *
     * @param phone 电话号码
     * @param model 短信模板
     */
    public static boolean sendSMS(String phone, int model) {
        String code = GetRandom.getRandom();
        for (SMSBean bean : SMSList) {
            if (bean.getPhone().equals(phone)) {
                if (bean.getTimeOut() && bean.getCount() <= 3) {
                    new Thread(() -> SendIdentifyCode.sendIdentifyCode(phone, code, model)).start();
                    bean.setCode(code);
                    bean.setTimeOut(false);            //重置倒计时
                    bean.addCount();
                    bean.setUsefulTime((new Date().getTime() + 10 * 60 * 1000));//有效时间10分钟
                    bean.setModel(model);
                    System.out.println(phone + "发送成功" + code);
                    return true;
                } else {
                    System.out.println(phone + "发送失败");
                    return false;
                }
            }
        }
        //未发送过短信
        new Thread(() -> SendIdentifyCode.sendIdentifyCode(phone, code, model)).start();
        SMSBean bean = new SMSBean(phone, code, model);
        SMSList.add(bean);                //添加到短信列表
        bean.addCount();
        System.out.println(phone + "发送成功" + code);
        return true;
    }

    /**
     * 获取验证码
     */
    public static String getCode(String phone) {
        String code = null;
        for (SMSBean bean : SMSList) {
            if (bean.getPhone().equals(phone)) {
                code = bean.getCode();
            }
        }
        return code;
    }

    /**
     * 获取电话号码一小时内发送验证码次数
     */
    public static int getCount(String phone) {
        if (SMSList == null) {
            SMSList = new ArrayList<>();
        }
        int count = 0;
        for (SMSBean bean : SMSList) {
            if (bean.getPhone().equals(phone)) {
                return bean.getCount();
            }
        }
        return count;
    }

    public static int getModel(String phone) {
        int model = -1;
        for (SMSBean bean : SMSList) {
            if (bean.getPhone().equals(phone)) {
                return bean.getModel();
            }
        }
        return model;
    }

    /**
     * 获取电话号码是否到发送时间
     */
    public static boolean getTimeout(String phone) {
        for (SMSBean bean : SMSList) {
            if (bean.getPhone().equals(phone)) {
                return bean.getTimeOut();
            }
        }
        return true;
    }

    /**
     * 从短信列表移除
     */
    public static void removeSMS(String phone) {
        for (int i = 0; i < SMSList.size(); i++) {
            SMSBean bean = SMSList.get(i);
            if (bean.getPhone().equals(phone)) {
                bean.hourTimer.interrupt();
                SMSList.remove(bean);
            }
        }
    }

    /**
     * 获取验证码有效时间
     */
    public static long getUsefulTime(String phone) {
        for (SMSBean bean : SMSList) {
            if (bean.getPhone().equals(phone)) {
                return bean.getUsefulTime();
            }
        }
        return 0;
    }

    public static class SMSBean {
        private String phone;
        private String code;
        private boolean timeOut = false;
        private int model;
        private int scened = 60;
        private int count = 0;
        private long usefulTime;

        public SMSBean() {
            super();
        }

        public void setUsefulTime(long l) {
            // TODO Auto-generated method stub
            usefulTime = l;
        }

        public SMSBean(String phone, String code, int model) {
            super();
            this.phone = phone;
            this.code = code;
            this.model = model;
            usefulTime = new Date().getTime() + 10 * 60 * 1000; //有效时间10分钟
            new Thread(timeoutRun).start();
            hourTimer.start();
        }

        /**
         * @return the usefulTime
         */
        public long getUsefulTime() {
            return usefulTime;
        }

        /**
         * @return the model
         */
        public int getModel() {
            return model;
        }

        /**
         * @param model the model to set
         */
        public void setModel(int model) {
            this.model = model;
        }

        public Thread hourTimer = new Thread(() -> {
            while (true) {
                Timer timer = new Timer();
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        count = 0;
                    }
                };
                timer.schedule(task, 60 * 60 * 1000);
                try {
                    Thread.sleep(60 * 60 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Runnable timeoutRun = () -> {
            // TODO Auto-generated method stub
            while (true) {
                scened--;
                if (scened == 0) {
                    timeOut = true;
                    break;
                } else {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        };

        /**
         * @return the count
         */
        public int getCount() {
            return count;
        }

        /**
         *
         */
        public void addCount() {
            this.count++;
        }

        /**
         * @return the phone
         */
        public String getPhone() {
            return phone;
        }

        /**
         * @param phone the phone to set
         */
        public void setPhone(String phone) {
            this.phone = phone;
        }

        /**
         * @return the code
         */
        public String getCode() {
            return code;
        }

        /**
         * @param code the code to set
         */
        public void setCode(String code) {
            this.code = code;
        }

        /**
         * @return the timeOut
         */
        public boolean getTimeOut() {
            return timeOut;
        }

        /**
         * @param timeOut the timeOut to set
         */
        public void setTimeOut(Boolean timeOut) {
            this.timeOut = timeOut;
            if (!timeOut) {
                scened = 60;
                new Thread(timeoutRun).start();
            }
        }

    }
}
