package org.antvillage.cards;

import org.antvillage.cards.core.CopperCard;
import org.antvillage.cards.core.DuchyCard;
import org.antvillage.cards.core.EstateCard;
import org.antvillage.cards.core.GoldCard;
import org.antvillage.cards.core.ProvinceCard;
import org.antvillage.cards.core.SilverCard;

/**
 * This class provides a central repository to all available cards. This is the class that is used throughout the code
 * to refer to actual cards. It hides the implementation class.
 *  
 * @author Verik
 *
 */
public class Cards {

	public static final Card COPPER = new CopperCard();
	public static final Card SILVER = new SilverCard();
	public static final Card GOLD = new GoldCard();
	public static final Card ESTATE = new EstateCard();
	public static final Card DUCHY = new DuchyCard();
	public static final Card PROVINCE = new ProvinceCard();
	public static final Card CURSE = new CopperCard();
	public static final Card SMITHY = new CopperCard();
}
