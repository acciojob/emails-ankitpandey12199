package com.driver;

import javax.xml.crypto.dsig.TransformService;
import java.util.*;
import java.util.Date;


class Mail
{
    Date date;
    String sender;
    String message;
    Mail(Date date,String sender,String message)
    {
        this.date=date;
        this.message=message;
        this.sender=sender;
    }
}
public class Gmail extends Email {

    int inboxCapacity; //maximum number of mails inbox can store
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)

    List<Mail>inbox;
    List<Mail>trash;
    public Gmail(String emailId, int inboxCapacity) {
       super(emailId);
       this.inboxCapacity=inboxCapacity;
       trash=new ArrayList<>();
       inbox=new ArrayList<>();
    }

    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.
        if(inboxCapacity==0)
        {
            trash.add(inbox.get(0));
            inbox.remove(0);
         inboxCapacity++;
        }
        inbox.add(new Mail(date, sender, message));
        inboxCapacity--;
    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing
        int index=-1;
        for (int i = 0; i < inbox.size(); i++) {
            if(inbox.get(i).message.equals(message))
            {
                index=i;
                break;
            }
        }
        if(index==-1)return;
        trash.add(inbox.get(index));
        inbox.remove(index);
        inboxCapacity++;
    }

    public String findLatestMessage(){
        if(inbox.isEmpty())return null;
        return inbox.get(inbox.size()-1).message;
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox

    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox
        if (inbox.isEmpty())return null;
        return inbox.get(0).message;
    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date
        int s=-1,count=0;
        for (int i = 0; i < inbox.size(); i++) {
            if(s==-1 && inbox.get(i).date.equals(start) )
            {
                s=i;
            }
            if(s!=-1 && inbox.get(i).date.before(end))
                count++;
        }
        return count;
    }

    public int getInboxSize(){
        // Return number of mails in inbox
        return inbox.size();
    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return trash.size();
    }

    public void emptyTrash(){
        // clear all mails in the trash
        trash.clear();
    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return inboxCapacity;
    }
}
