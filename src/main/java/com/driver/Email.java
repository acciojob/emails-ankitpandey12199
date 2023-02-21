package com.driver;

public class Email {

    private String emailId;
    private String password;

    public Email(String emailId){
        this.emailId = emailId;
        this.password = "Accio@123";
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }
    public boolean isValid(String pass)
    {
        boolean lower=false,upper=false,digit=false,special=false;
        for(char a:pass.toCharArray())
        {
            if(!Character.isLetterOrDigit(a))
            {
                special=true;
            }
            if(Character.isDigit(a))
            {
                digit=true;
            }
            if (Character.isUpperCase(a))
            {
                upper=true;
            }
            if(Character.isLowerCase(a))
            {
                lower=true;
            }
        }
        return lower&&upper&&special&&digit;
    }
    public void changePassword(String oldPassword, String newPassword){
        if(!oldPassword.equals(password))return;
        if(newPassword.length()<8 || !isValid(newPassword))return;
        this.password=newPassword;
        //Change password only if the oldPassword is equal to current password and the new password meets all of the following:
        // 1. It contains at least 8 characters
        // 2. It contains at least one uppercase letter
        // 3. It contains at least one lowercase letter
        // 4. It contains at least one digit
        // 5. It contains at least one special character. Any character apart from alphabets and digits is a special character
    }
}
