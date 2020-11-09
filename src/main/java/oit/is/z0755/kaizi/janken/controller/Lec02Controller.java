package oit.is.z0755.kaizi.janken.controller;

//import java.util.ArrayList;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import oit.is.z0755.kaizi.janken.model.Janken;
import oit.is.z0755.kaizi.janken.model.Entry;
import oit.is.z0755.kaizi.janken.model.UserMapper;
import oit.is.z0755.kaizi.janken.model.Match;
import oit.is.z0755.kaizi.janken.model.MatchMapper;
import oit.is.z0755.kaizi.janken.model.MatchInfo;
import oit.is.z0755.kaizi.janken.model.MatchInfoMapper;

@Controller
public class Lec02Controller {

  @Autowired
  private Entry entry;

  @Autowired
  private UserMapper userMapper;

  @Autowired
  private MatchMapper matchMapper;

  @Autowired
  private MatchInfoMapper matchInfoMapper;

  private int user_id;

  @GetMapping("/lec02")
  @Transactional
  public String lec02(Principal prin, ModelMap model) {
    String loginUser = prin.getName();
    user_id = userMapper.selectIdByName(loginUser);
    this.entry.addUser(loginUser);
    model.addAttribute("entry", this.entry);
    model.addAttribute("login_user", loginUser);
    model.addAttribute("users", userMapper.selectAllUsers());
    model.addAttribute("matches", matchMapper.selectAllMatches());
    return "lec02.html";
  }

  @GetMapping("/match")
  @Transactional
  public String match(@RequestParam Integer id, Principal prin, ModelMap model) {
    MatchInfo matchInfo = new MatchInfo();
    matchInfo.setUser_1(user_id);
    matchInfo.setUser_2(id);
    matchInfo.setActive(true);
    matchInfoMapper.insertMatchInfo(matchInfo);

    model.addAttribute("cpu_id", id);
    model.addAttribute("user_name", prin.getName());
    model.addAttribute("cpu_name", userMapper.selectAllUsers().get(id - 1).getName());
    return "match.html";
  }

  @GetMapping("/lec02_1")
  @Transactional
  public String lec02_1(@RequestParam Integer cpu_id, @RequestParam Integer janken, Principal prin, ModelMap model) {

    Janken jk = new Janken(janken);

    Match match = new Match();
    String user_hand = jk.myhand();
    String cpu_hand = jk.yourhand();

    match.setUser_1(user_id);
    match.setUser_2(cpu_id);
    match.setUser_1_hand(user_hand);
    match.setUser_2_hand(cpu_hand);

    // データベースに試合結果を保存する
    matchMapper.insertMatch(match);

    model.addAttribute("cpu_id", cpu_id);
    model.addAttribute("user_name", prin.getName());
    model.addAttribute("cpu_name", userMapper.selectNameById(cpu_id));
    model.addAttribute("myhand", user_hand);
    model.addAttribute("yourhand", cpu_hand);
    model.addAttribute("JankenResult", jk.judge());
    return "match.html";
  }

}
