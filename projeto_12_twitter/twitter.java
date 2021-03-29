package projeto_12_twitter;
import java.util.Map;
import java.util.TreeMap;
import java.util.Scanner;
import java.util.TreeSet;

class Tweet { 
  int idTw;
  String username;
  String msg;
  TreeSet <String> likes;
  
  public Tweet(int idTw, String username, String msg){
      this.idTw=idTw;
      this.username=username;
      this.msg=msg;
      this.likes= new TreeSet<>();
  }
  
   void like(String username){
      likes.add(username);
  }
  
   int getIdTw(){
    return idTw;
   }

   public void setIdTw (int idTw){
    this.idTw = idTw;
   }
   
   String getUsername(){
    return username;
   }

   public void setUsername (String username){
    this.username= username;
   }
   
   String getMsg(){
    return msg;
   }

   public void setMsg (String msg){
    this.msg= msg;
   }
  
   public String toString(){
       return idTw + ":" + username + "(" + msg + ")" + likes + "\n"; 
   }   
}

class Controller {
    Map<String,User>users;
    Map<Integer,Tweet>tweets;
    int nextTwId;
    
  public Controller(){
       this.users = new TreeMap<>();
       this.tweets = new TreeMap<>();
       this.nextTwId= nextTwId;
  }
   void sendTweet(String username, String msg){
        if(users.containsKey(username)){
            Tweet tweet= new Tweet(nextTwId,username,msg);
            tweets.put(nextTwId, tweet);
            User user= getUser(username);
            user.sendTweet(tweet);
            nextTwId += 1;  
        } else {
            new RuntimeException("Usuario não encontrado");
        }
    }
    
   void addUser(String username){
        User user= new User(username);
        if(!users.containsKey(username)){
            users.put(username, user);
        }
    }
    
   User getUser(String username){
      User user = users.get(username);
      if(user!=null){
          return user;
      }else{
        throw new RuntimeException("Usuario não encontrado");  
      }
    }
   public String toString(){
       String alt= "";
       for(User user: users.values()){
           alt += user + "\n";
       }
       return alt;
   }  
}

class User{
    String username;
    Map<String,User>followers;
    Map<String,User>following;
    Map<Integer,Tweet>timeline;
    int unreadCount;
    
  public User(){
       this.followers= new TreeMap<>();
       this.following= new TreeMap<>();
       this.timeline = new TreeMap<>();
       this.username= username;
       this.unreadCount= 0;
  }

    User(String username) {
        throw new UnsupportedOperationException("Usuario não encontrado ");
    }

  
    void follow(User user){
        if(!following.containsKey(user.getUsername())){
            following.put(user.getUsername(), this);
            user.followers.put(this.getUsername(), this);
        } else {
        new RuntimeException("Usuario ja segue");
        }
    }
    
    private String getUsername() {
        return username;
    }

    void unfollow(String username){
        User user = new User();
        if(following.containsKey(user.getUsername())){
            user.followers.remove(this.getUsername(), this);
        } else {
        new RuntimeException("Usuario já não seguia segue");
        }
    }
    
    Tweet getTweet(int idTw){
        Tweet tweet = timeline.get(idTw);
        tweet.like(getUsername());
        return tweet;
    }
    
    void sendTweet(Tweet tweet){
        for(User user : followers.values()){
            user.getUsername();
            user.unreadCount +=1;
            user.timeline.put(tweet.getIdTw(),tweet);
        }
    }
    
    String getUnread(){
        String alt = "";
        unreadCount = 0;
        Map<Integer, Tweet> timeline2 = new TreeMap<>();
        for(int i = timeline.size() - this.unreadCount; i< timeline.size(); i++){
            timeline2.put(i, timeline.get(i));
        }
        return alt;
    }
    
    String getTimeline(){
        String alt = "";
        unreadCount = 0;
        Map<Integer, Tweet> timeline2 = new TreeMap<>();
        for(int i = timeline.size() - this.unreadCount; i< timeline.size(); i++){
            timeline2.put(i, timeline.get(i));
        }
        for(Tweet tweets : timeline2.values()){
            alt += tweets;
        }
        if(this.unreadCount == 0){
            throw new RuntimeException ("Não tem nada novo por aqui");
        }
        return alt;
    }

    public String toString(){
        String alt = "";
        alt += getUsername() + "\n" + "Seguidores: " + followers.keySet() + "; Seguidos: " + following.keySet();
        return alt;
    }
}

public class twitter{
 public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Controller sistema = new Controller();
    
    while(true){
        String line = scanner.nextLine();
        System.out.println("$" + line);
        String ui[] = line.split(" ");
        try {
            if (ui[0].equals("end"))
                break;
            else if (ui[0].equals("addUser")) {
                sistema.addUser(ui[1]);
            } else if (ui[0].equals("show")) {
                System.out.print(sistema);
            } else if (ui[0].equals("follow")) {
                User one = sistema.getUser(ui[1]);
                User two = sistema.getUser(ui[2]);
                one.follow(two);
            }
            else if (ui[0].equals("twittar")) {
                String username = ui[1];
                String msg = "";
                for(int i = 2; i < ui.length; i++)
                    msg += ui[i] + " ";
                sistema.sendTweet(username, msg);
            }
            else if (ui[0].equals("timeline")) {
                User user = sistema.getUser(ui[1]);
                System.out.print(user.getTimeline());
            }
            else if (ui[0].equals("like")) {
                User user = sistema.getUser(ui[1]);
                Tweet tw = user.getTweet(Integer.parseInt(ui[2]));
                tw.like(ui[1]);
            }else if (ui[0].equals("unfollow")) {
                User user = sistema.getUser(ui[1]);
                user.unfollow(ui[2]);
            }else{
                System.out.println("fail: comando invalido");
            }
        }catch(RuntimeException rt){
            System.out.println(rt.getMessage());
        }
    }
    scanner.close();
}
}

