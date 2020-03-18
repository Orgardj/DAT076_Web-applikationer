package controller;

import com.view.ThreadBackingBean;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.omnifaces.cdi.Push;
import org.omnifaces.cdi.PushContext;
import model.entity.Thread;

@Named
@RequestScoped
public class ThreadController implements Serializable {

    @Inject
    private ThreadBackingBean threadBackingBean;

    @Inject
    @Push(channel = "main")
    private PushContext push;

    public void removeThread(Thread thread) {
        threadBackingBean.removeThread(thread);
        push.send("update_threads");
    }
}
