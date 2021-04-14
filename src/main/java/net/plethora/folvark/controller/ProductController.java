package net.plethora.folvark.controller;

import net.plethora.folvark.dao.DaoProductMap;
import net.plethora.folvark.dao.repo.CommentRepository;
import net.plethora.folvark.models.Comment;
import net.plethora.folvark.models.ProductMap;
import net.plethora.folvark.models.User;
import net.plethora.folvark.models.system.CommentData;
import net.plethora.folvark.service.AuthService;
import net.plethora.folvark.service.CommentService;
import net.plethora.folvark.service.ConverterJsonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;


@Controller
public class ProductController {

    private final DaoProductMap daoProductMap;
    private final AuthService authService;
    private final CommentService commentService;
    private final CommentRepository commentRepository;
    private final ConverterJsonService<CommentData> converterJsonService;

    public ProductController(DaoProductMap daoProductMap, AuthService authService, CommentService commentService,
                             CommentRepository commentRepository,ConverterJsonService<CommentData> converterJsonService) {
        this.daoProductMap = daoProductMap;
        this.authService = authService;
        this.commentService = commentService;
        this.commentRepository = commentRepository;
        this.converterJsonService = converterJsonService;
    }

    @GetMapping("/product/{id}")
    public String viewProduct(@PathVariable(value = "id") String id, HttpSession httpSession, Model model) {
        authService.controlUser(httpSession, model);
//        authService.checkCart(httpSession);
//        authService.viewUserAccount(model);
        int countProduct = authService.countProduct(httpSession);
        ProductMap product = daoProductMap.findById(id);
        model.addAttribute("product", product);
        model.addAttribute("countProducts", countProduct);
        model.addAttribute("comments", commentService.getCommentsFromElementDESC(id));
        return "product-page";

    }

    @PostMapping("/product/{id}/addComment")
    public @ResponseBody
    void addComment(@PathVariable("id") String id, @RequestBody String comment) {
        String com = comment.replace("\"", "");
        Comment comment1 = new Comment();
        comment1.setText(com);
        comment1.setAttachedTo(id);
        commentRepository.save(comment1);

    }

    @PostMapping("/product/{id}/addReplyToComment")
    public @ResponseBody String addReplyToComment(@PathVariable("id") String id, @RequestBody String replyComment){
       System.out.println("Старт");
        User user = authService.getAuthUser();
        CommentData commentData = new CommentData();
        commentData.setName(user.getFirstName() + " " + user.getLastName());
        commentData.setDate(new Date());
       return converterJsonService.toJSON(commentData);

    }
}