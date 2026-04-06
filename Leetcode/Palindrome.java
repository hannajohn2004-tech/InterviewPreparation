class Solution
{
    public boolean isPalindrome(int x) 
    {
        int rev=0;
        int rem=0;
        int a=x;
        while(x>0)
        {
            rem=x%10;
            if(rev > Integer.MAX_VALUE/10 || rev < Integer.MIN_VALUE/10)
                return false;
            rev=rev*10+rem;
            x=x/10;
        }
        if(a==rev)
        {
            return true;
        }
        else
        {
            return false;
        }
        
    }
}
