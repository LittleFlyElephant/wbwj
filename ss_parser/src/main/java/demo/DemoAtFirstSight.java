package demo;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;

import java.util.List;

/**
 * Created by dengrong on 2017/1/3.
 */
public class DemoAtFirstSight
{
    public static void main(String[] args)
    {
        System.out.println("首次编译运行时，HanLP会自动构建词典缓存，请稍候……");
//        HanLP.Config.enableDebug();         // 为了避免你等得无聊，开启调试模式说点什么:-)
        System.out.println(HanLP.segment("你好，欢迎使用HanLP汉语处理包！接下来请从其他Demo中体验HanLP丰富的功能~"));

        List<Term> termList = NLPTokenizer.segment("1994年8月9日，原告刘某某向永吉县土地管理局递交《住宅用地申请书》，申请对位于缸窑镇二道村三社的房屋进行翻建。");
        System.out.println(termList);
    }
}
