package org.antvillage.cards;

import org.antvillage.cards.core.CopperCard;
import org.antvillage.cards.core.DuchyCard;
import org.antvillage.cards.core.EstateCard;
import org.antvillage.cards.core.GoldCard;
import org.antvillage.cards.core.ProvinceCard;
import org.antvillage.cards.core.SilverCard;

/**
 * This enum provides a central 
 * @author Erik
 *
 */
public enum Cards {

	COPPER(new CopperCard()),
	SILVER(new SilverCard()),
	GOLD(new GoldCard()),
	ESTATE(new EstateCard()),
	DUCHY(new DuchyCard()),
	PROVINCE(new ProvinceCard()),
	CURSE(new Card()),
	SMITHY(new Card()),
	;
	
	public final Card card;

	private Cards(Card card) {
		this.card = card;
	}
}
