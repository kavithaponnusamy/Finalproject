package co.grandcircus.FinalProject.entity;

public class Sample {
	public static void main (String []args) {
		System.out.println(isPalindrome("MOM"));
	}
	public static boolean isPalindrome(String word) {
		String str="";
		for(int i=word.length()-1;i>0;i--) {
			str+=word.charAt(i);
		}
		if(str.equalsIgnoreCase(word)) {
			return true;
		} else {
			return false;
		}
	}

}
