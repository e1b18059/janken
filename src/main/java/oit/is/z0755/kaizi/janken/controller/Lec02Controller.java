package oit.is.z0755.kaizi.janken.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import oit.is.z0755.kaizi.janken.model.Janken;
import oit.is.z0755.kaizi.janken.model.Entry;

@Controller
public class Lec02Controller {

  @Autowired
  private Entry entry;

  @GetMapping("/lec02_1")
  public String lec02_1(@RequestParam Integer janken, ModelMap model) {

    Janken jk = new Janken(janken);
    model.addAttribute("myhand", jk.myhand());
    model.addAttribute("yourhand", jk.yourhand());
    model.addAttribute("JankenResult", jk.judge());
    return "lec02.html";
  }

  @GetMapping("/lec02")
  public String lec02(Principal prin, ModelMap model) {
    String loginUser = prin.getName();
    this.entry.addUser(loginUser);
    model.addAttribute("entry", this.entry);

    return "lec02.html";
  }

  @PostMapping("/lec02")
  public String lec02(@RequestParam String user_name, ModelMap model) {
    model.addAttribute("user_name", user_name);
    return "lec02.html";
  }

}
