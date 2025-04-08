package com.rentbook.demo.busines.abstracts;

public interface IEmailService {

    public void sendLateReturnEmail(String to, String subject, String body);
}
