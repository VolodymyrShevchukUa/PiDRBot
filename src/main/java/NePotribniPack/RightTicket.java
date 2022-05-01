//package NePotribniPack;
//
//import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
//import org.telegram.telegrambots.meta.api.objects.InputFile;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//public class RightTicket {
// private List<Question> allQuestionsList = new JSONtry("src/main/resources/Questions.json").questions1;
//    // Тут з чат айді я щось нашаманив пізда
//    private List<SendPhoto> allPhotoList = getPhotoList(allQuestionsList,1231);
//    int rand = (int) (Math.random() * allQuestionsList.size());
//    Set<Integer> ticketIndicator = new HashSet<>();
//
//
//
//    private List<SendPhoto> getPhotoList(List<Question> questions, long chatId){
//        int i = 0;
//        List<SendPhoto> photoList = new ArrayList<>();
//        for (Question que:questions){
//            photoList.add(new SendPhoto().builder()
//                    .replyMarkup(keyBoardList.getMarkupListTemplate(allQuestionsList.size()).get(i))
//                    .photo(new InputFile(que.getUrl()))
//                    .caption(que.getCaption())
//                    .chatId(chatId+"")
//                    .build());
//            i++;
//        }
//        return photoList;
//    }
//
//
//
//
//    public boolean isEnd(){
//        return false;
//    }
//    // Я так розумію ця залупа має викликатись в основному класі приблизно так
//    //ОБОВ'Язково реалізувати хеш сет для того щоб не записувалось в білет два однакових питання!!!
//    public SendPhoto getNextQuestion(int index){
////            if (!ticketIndicator.contains(ticket.rand)){
////                continue
////            }
//        SendPhoto ini;
//        ini = allPhotoList.get(index);
//        ticketIndicator.add(index);
//        return ini;
//
//    }
//    public List<SendPhoto> testFinalTicket(){
//        List<SendPhoto> sendPhotos = new ArrayList<>();
//        // Або тут якраз пиздувати статік???
//
//
//        // Тут можливо треба буде реалізувати While ListMessage,size == 20;....
//        for(int i = 0 ;i<20;i++){
//            int a = (int) (Math.random() * allQuestionsList.size());
//            // тут вроді смислу особо нема в визові нового тікет
//            if(!ticketIndicator.contains(a)){
//                sendPhotos.add(getNextQuestion(a));
//
//                System.out.println(ticketIndicator);}
//            else {continue;}
//        }
//        return  sendPhotos;
//    }
//
//}
