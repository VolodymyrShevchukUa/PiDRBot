package entity;

import adapter.message.MessageI;

import java.util.*;

public class Ticket {
    private final List<Question> questions;

    public Ticket(List<Question> allQuestionsList, int countOfQuestions) {
        Set<Integer> ticketIndicator = new HashSet<>();
        this.questions = new ArrayList<>(countOfQuestions);
        int size = allQuestionsList.size();
        while (questions.size() < countOfQuestions) {
            int a = (int) (Math.random() * size);
            if (!ticketIndicator.contains(a)) {
                questions.add(allQuestionsList.get(a));
                ticketIndicator.add(a);
            }
        }
    }

    public Queue<MessageI> getQueueOfTicketMessages(long chatID) {
        Queue<MessageI> queueOfTicketMessages = new LinkedList<>();
        for (Question q : questions) {
            queueOfTicketMessages.add(q.createMessage(chatID));
        }
        System.out.println();
        return queueOfTicketMessages;

    }
}

