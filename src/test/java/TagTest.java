import com.imeiman.ssm.blog.domain.entity.*;
import com.imeiman.ssm.blog.mapper.*;
import com.imeiman.ssm.blog.service.ArticleService;
import com.imeiman.ssm.blog.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class TagTest {

    @Autowired
    ArticleService articleService;

    @Autowired
    UserMapper userMapper;


    @Test
    public void test01(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        TagMapper tagMapper = sqlSession.getMapper(TagMapper.class);

        List<Tag> allTags = tagMapper.getAllTags();
        for (Tag tag : allTags){
            System.out.println(tag);
        }
    }

    @Test
    public void test02(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);
        List<Article> articles = mapper.pageArticle(1, 1, 10);
//        for (Article article:articles){
//            System.out.println(article.getArticleId());
        Integer articleCount = mapper.getArticleCount();
        System.out.println(articleCount);
        Integer allByArticleCommentCountInteger = mapper.getAllByArticleCommentCountInteger();
        System.out.println(allByArticleCommentCountInteger);

        Date articleLastUpdateTimeDate = mapper.getArticleLastUpdateTimeDate();
        System.out.println(articleLastUpdateTimeDate);

        List<Article> articles1 = mapper.listArticleByViewCount(8);
        System.out.println(articles1.size());
//        }
    }

    @Test
    public void test03(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        CategoryMapper mapper = sqlSession.getMapper(CategoryMapper.class);
        List<Category> categoryByArticleIdList = mapper.getCategoryByArticleIdList(32);
        for (Category category:categoryByArticleIdList){
            System.out.println(category);
        }



    }
    @Test
    public void test04(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User byId = mapper.getById(1);
        System.out.println(byId);


    }


    @Test
    public void test05(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        OptionsMapper mapper = sqlSession.getMapper(OptionsMapper.class);
        Options options = mapper.getOptions();
        System.out.println(options);


    }


}
