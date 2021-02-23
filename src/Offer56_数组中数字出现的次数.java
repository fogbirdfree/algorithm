public class Offer56_数组中数字出现的次数 {
    /**
     * 找出两个只出现了一次的数字
     * @param nums
     * @return
     */
    public int[] singleNumbers(int[] nums) {
        //xor用来计算nums的异或和
        int xor = 0;

        // 计算异或和 并存到xor
        // e.g. [2,4,2,3,3,6] 异或和：(2^2)^(3^3)^(4^6)=2=010
        for(int num : nums) xor ^= num;

        //设置mask为1，则二进制为0001
        // mask是一个二进制数，且其中只有一位是1，其他位全是0，比如000010，
        // 表示我们用倒数第二位作为分组标准，倒数第二位是0的数字分到一组，倒数第二位是1的分到另一组
        int mask = 1;

        // & operator只有1&1时等于1 其余等于0
        // 用上面的e.g. 4和6的二进制是不同的 我们从右到左找到第一个不同的位就可以分组 4=0100 6=0110
        // 根据e.g. 010 & 001 = 000 = 0则 mask=010
        // 010 & 010 != 0 所以mask=010
        // 之后就可以用mask来将数组里的两个数分区分开
        while((xor & mask)==0){
            mask <<= 1;
        }

        //两个只出现一次的数字
        int a=0, b=0;

        for(int num : nums){
            //根据&是否为0区分将两个数字分区，并分别求异或和
            if((num & mask)==0) a ^= num;
            else{
                b ^= num;
            }
        }
        return new int[]{a,b};
    }
}
