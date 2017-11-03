import com.org.json.JSONArray;
import com.org.json.JSONObject;
import com.ricky.model.GMessage;
import com.ricky.model.Player;
import com.ricky.service.GHService;
import com.ricky.service.UserService;

import java.nio.ByteBuffer;

public class Test {
    public static void main(String[] args)
    {
        GMessage message = new GMessage("message", GMessage.GType.START);
        JSONObject jsonObj = message.toJSON();
        System.out.println(jsonObj.toString());

        GMessage mess = new GMessage(jsonObj);
        System.out.println(mess.toString());
    }
}
