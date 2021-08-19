@AnyMetaDef(name = "player",
metaType = "string",
idType = "long",
metaValues = {
		@MetaValue(value = "Chess", targetEntity = ChessPlayer.class),
		@MetaValue(value = "Monopoly", targetEntity = MonopolyPlayer.class)
}
		)
@AnyMetaDef(name = "team",
metaType = "string",
idType = "long",
metaValues = {
		@MetaValue(value = "Chess", targetEntity = ChessPlayer.class),
		@MetaValue(value = "Monopoly", targetEntity = MonopolyPlayer.class)
})      
package examples.hibernate.domainmodel.inheritance.any;
import org.hibernate.annotations.AnyMetaDef;
import org.hibernate.annotations.MetaValue;

import examples.hibernate.domainmodel.inheritance.any.AnyEntities.ChessPlayer;
import examples.hibernate.domainmodel.inheritance.any.AnyEntities.MonopolyPlayer;




