package org.antvillage.cards;

import org.antvillage.cards.core.CopperCard;
import org.antvillage.cards.core.DuchyCard;
import org.antvillage.cards.core.EstateCard;
import org.antvillage.cards.core.GoldCard;
import org.antvillage.cards.core.ProvinceCard;
import org.antvillage.cards.core.SilverCard;

/**
 * This enum provides a central repository to all available cards. This is the class that is used throughout the code
 * to refer to actual cards. It hides the implementation class.
 *  
 * @author Verik
 *
 */
public enum Card {

	COPPER(new CopperCard()),
	SILVER(new SilverCard()),
	GOLD(new GoldCard()),
	ESTATE(new EstateCard()),
	DUCHY(new DuchyCard()),
	PROVINCE(new ProvinceCard()),
	CURSE(new CopperCard()),
	SMITHY(new CopperCard()),
	;
	
	public final BaseCard implementation;

	private Card(BaseCard card) {
		this.implementation = card;
	}
}
