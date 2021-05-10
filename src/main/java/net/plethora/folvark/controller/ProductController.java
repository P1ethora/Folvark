package net.plethora.folvark.controller;

import net.plethora.folvark.dao.DaoProductMap;
import net.plethora.folvark.dao.repo.CommentRepository;
import net.plethora.folvark.models.Comment;
import net.plethora.folvark.models.ProductMap;
import net.plethora.folvark.models.Reply;
import net.plethora.folvark.models.User;
import net.plethora.folvark.models.system.CommentData;
import net.plethora.folvark.service.AuthService;
import net.plethora.folvark.service.CommentService;
import net.plethora.folvark.service.ConverterJsonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


@Controller
public class ProductController {

    private final DaoProductMap daoProductMap;
    private final AuthService authService;
    private final CommentService commentService;
    private final CommentRepository commentRepository;
    private final ConverterJsonService<CommentData> converterJsonService;

    public ProductController(DaoProductMap daoProductMap, AuthService authService, CommentService commentService,
                             CommentRepository commentRepository, ConverterJsonService<CommentData> converterJsonService) {
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
    String addComment(@PathVariable("id") String id, @RequestBody String comment) {
        Date date = new Date();
        SimpleDateFormat formater = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        String com = comment.replace("\"", "");
        Comment comment1 = new Comment();
        comment1.setName(authService.getAuthUser().getFirstName() + " "
                + authService.getAuthUser().getLastName());
        comment1.setText(com);
        comment1.setAttachedTo(id);
        comment1.setAnswers(new ArrayList<>());
        comment1.setDate(date);
        comment1.setIdUser(authService.getAuthUser().getId());
        commentRepository.save(comment1);

        CommentData commentData = new CommentData();
        commentData.setId(commentService.getComment(comment1.getIdUser(),date,comment1.getAttachedTo()).getId());
        commentData.setName(comment1.getName());
        commentData.setDate(formater.format(date));

        return converterJsonService.toJSON(commentData);

    }

    @PostMapping("/product/{id}/addReplyToComment")
    public @ResponseBody
    String addReplyToComment(@PathVariable("id") String id, @RequestBody String json) {
        Date date = new Date();
        System.out.println(json);
        SimpleDateFormat formater = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        User user = authService.getAuthUser();

        CommentData commentData = new CommentData();
        commentData.setName(user.getFirstName() + " " + user.getLastName());
        commentData.setDate(formater.format(date));

        Reply reply = converterJsonService.formJSON(json);
        reply.setIdUser(authService.getAuthUser().getId());
        reply.setDate(date);

        Comment comment = commentService.getComment(reply.getIdParent());
        System.out.println(comment);
        reply.setId(String.valueOf(comment.getAnswers().size() + 1));
        commentData.setId(reply.getId());
        comment.getAnswers().add(reply);

        commentService.saveComment(comment);

//        commentService.getComment(comment.)


        return converterJsonService.toJSON(commentData);

    }
}