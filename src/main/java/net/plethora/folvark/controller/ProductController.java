package net.plethora.folvark.controller;

import net.plethora.folvark.dao.DaoProductMap;
import net.plethora.folvark.dao.repo.CommentRepository;
import net.plethora.folvark.models.Comment;
import net.plethora.folvark.models.ProductMap;
import net.plethora.folvark.service.AuthService;
import net.plethora.folvark.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@Controller
public class ProductController {

    private final DaoProductMap daoProductMap;
    private final AuthService authService;
    private final CommentService commentService;

    public ProductController(DaoProductMap daoProductMap, AuthService authService, CommentService commentService) {
        this.daoProductMap = daoProductMap;
        this.authService = authService;
        this.commentService = commentService;
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
        model.addAttribute("comments", commentService.getCommentsFromElement(id));
        return "product-page";

    }
}