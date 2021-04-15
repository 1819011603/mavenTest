package Algorithm;

public class KMP01 {
    public static int findSubstring(String s, String subString){
        boolean is ;
        for(int i = 0; i < s.length(); i++){
            is = true;
            for(int j = 0; j < subString.length(); j++){
                if(i + j < s.length() && s.charAt(i+j) != subString.charAt(j)){
                    is = false;
                    break;
                }

            }
            if(is){
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        String s ="ababcabacbds";
        String sub = "abac";
        System.out.println(findSubstring(s,sub));
    }
}
