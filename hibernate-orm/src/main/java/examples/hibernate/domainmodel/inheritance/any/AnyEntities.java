package examples.hibernate.domainmodel.inheritance.any;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Any;

import lombok.Getter;
import lombok.Setter;

class AnyEntities {
	static interface Player {
		Integer getWins();
		Integer getLoses();
	}

	@Entity@Getter@Setter
	static class ChessPlayer implements Player {

		@Id
		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chess_player_seq")
		@SequenceGenerator(name = "chess_player_seq", sequenceName = "chess_player_seq", initialValue = 100)
		private Long id;
		private String firstName;
		private String lastName;
		private Integer wins;
		private Integer loses;

	}
	/**
	    create table "ChessPlayer" (
	       "id" bigint not null,
	        "firstName" varchar(255),
	        "lastName" varchar(255),
	        "loses" integer,
	        "wins" integer,
	        primary key ("id")
	    )
	 */
	/**
	 *  create table "PlayerScore" (
       "id" bigint not null,
        "player_type" varchar(255),
        "player_id" bigint,
        "score" integer,
        primary key ("id")
    	)
	 *
	 */
	@Entity@Getter@Setter
	static class MonopolyPlayer implements Player {

		@Id
		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "monopoly_player_seq")
		@SequenceGenerator(name = "monopoly_player_seq", sequenceName = "monopoly_player_seq", initialValue = 100)
		private Long id;
		private String firstName;
		private String lastName;
		private  Integer wins;
		private Integer loses;
	}

	@Entity@Getter@Setter
	static class PlayerScore {
		@Id
		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "player_score_seq")
		@SequenceGenerator(name = "player_score_seq", sequenceName = "player_score_seq", initialValue = 100)
		private Long id;
		private Integer score;
		@Any(metaDef = "player", metaColumn = @Column(name = "player_type"), fetch = FetchType.LAZY)
		@JoinColumn(name = "player_id")
		private Player player;

	}
}
