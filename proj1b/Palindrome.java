public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> deque = new LinkedListDeque<Character>();
        for (int i = 0; i < word.length(); i += 1) {
            deque.addLast(word.charAt(i));
        }
        return deque;
    }

    private boolean isPalindrome(Deque<Character> wordDeque) {
        if (wordDeque.size() == 1 || wordDeque.size() == 0) {
            return true;
        }
        if (wordDeque.removeFirst() == wordDeque.removeLast()) {
            return isPalindrome(wordDeque);
        }
        return false;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> wordDeque = wordToDeque(word);
        return isPalindrome(wordDeque);
    }

    private boolean isPalindrome(Deque<Character> wordDeque, CharacterComparator cc) {
        if (wordDeque.size() == 1 || wordDeque.size() == 0) {
            return true;
        }
        if (cc.equalChars(wordDeque.removeFirst(), wordDeque.removeLast())) {
            return isPalindrome(wordDeque, cc);
        }
        return false;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> wordDeque = wordToDeque(word);
        return isPalindrome(wordDeque, cc);
    }
}
