package forms;

import play.data.validation.Constraints.*;

public class ContactUs {
    @Required
    @Email
    public String email;
    
    @Required
    public String content;
}
