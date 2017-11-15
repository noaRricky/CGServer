import com.ricky.model.Message;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args)
    {
        List<Integer> deck = new ArrayList<>();
        deck.add(1);
        deck.add(2);
        deck.add(3);
        Message message = new Message(Message.Type.DECK, "Ricky", deck);
        String str = message.toJSON();
        Message next = new Message(str);
        System.out.println(next.toJSON());
    }
}
