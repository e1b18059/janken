package oit.is.z0755.kaizi.janken.controller;

//import java.util.ArrayList;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import oit.is.z0755.kaizi.janken.model.Janken;
import oit.is.z0755.kaizi.janken.model.Entry;
import oit.is.z0755.kaizi.janken.model.UserMapper;
import oit.is.z0755.kaizi.janken.model.MatchMapper;

@Controller
public class Lec02Controller {

  @Autowired
  private Entry entry;

  @Autowired
  private UserMapper userMapper;

  @Autowired
  private MatchMapper matchMapper;

  @GetMapping("/lec02")
  public String lec02(Principal prin, ModelMap model) {
    String loginUser = prin.getName();
    this.entry.addUser(loginUser);
    model.addAttribute("entry", this.entry);
    model.addAttribute("login_user", loginUser);
    model.addAttribute("users", userMapper.selectAllUsers());
    model.addAttribute("matches", matchMapper.selectAllMatches());
    return "lec02.html";
  }

  @GetMapping("/match")
  public String match(@RequestParam Integer id, Principal prin, ModelMap model) {
    model.addAttribute("user_name", prin.getName());
    model.addAttribute("cpu_name", userMapper.selectAllUsers().get(id - 1).getName());
    return "match.html";
  }

  @GetMapping("/lec02_1")
  public String lec02_1(@RequestParam Integer janken, ModelMap model) {

    Janken jk = new Janken(janken);
    model.addAttribute("myhand", jk.myhand());
    model.addAttribute("yourhand", jk.yourhand());
    model.addAttribute("JankenResult", jk.judge());
    return "lec02.html";
  }

}
