package oit.is.z0755.kaizi.janken.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface MatchMapper {

  @Select("SELECT * from matches")
  ArrayList<Match> selectAllMatches();

  @Insert("INSERT INTO matches (user_1,user_2,user_1_hand,user_2_hand) VALUES (#{user_1},#{user_2},#{user_1_hand},#{user_2_hand});")
  @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
  void insertMatch(Match match);

}
