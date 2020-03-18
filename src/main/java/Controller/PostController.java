package controller;

import com.view.PostBackingBean;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.entity.Post;
import org.omnifaces.cdi.Push;
import org.omnifaces.cdi.PushContext;

@Named
@RequestScoped
public class PostController implements Serializable {

    @Inject
    private PostBackingBean postBackingBean;

    @Inject
    @Push(channel = "main")
    private PushContext push;

    public void newPost() {
        postBackingBean.createPost();
        push.send("update_posts");
    }

    public void removePost(Post post) {
        postBackingBean.removePost(post);
        push.send("update_posts");
    }
    
    public void editPost(Post post) {
        if (!postBackingBean.getEditedMessage().equals(post.getText()) 
                && !postBackingBean.getEditedMessage().equals("") ) 
            postBackingBean.editPost(post);
        push.send("update_posts");
        postBackingBean.setEditedMessage("");
    }
}
