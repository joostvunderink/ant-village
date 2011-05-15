package org.antvillage.cards;

import org.antvillage.cards.base.FestivalCard;
import org.antvillage.cards.base.LaboratoryCard;
import org.antvillage.cards.base.MarketCard;
import org.antvillage.cards.base.SmithyCard;
import org.antvillage.cards.base.VillageCard;
import org.antvillage.cards.base.WitchCard;
import org.antvillage.cards.base.WoodcutterCard;
import org.antvillage.cards.core.CopperCard;
import org.antvillage.cards.core.CurseCard;
import org.antvillage.cards.core.DuchyCard;
import org.antvillage.cards.core.EstateCard;
import org.antvillage.cards.core.GoldCard;
import org.antvillage.cards.core.ProvinceCard;
import org.antvillage.cards.core.SilverCard;
import org.antvillage.cards.intrigue.GreatHallCard;
import org.antvillage.cards.intrigue.HaremCard;
import org.antvillage.cards.seaside.BazaarCard;

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
	
	public static final Card CURSE = new CurseCard();
	
	// Base set
	public static final Card FESTIVAL = new FestivalCard();
	public static final Card LABORATORY = new LaboratoryCard();
	public static final Card MARKET = new MarketCard();
	public static final Card SMITHY = new SmithyCard();
	public static final Card VILLAGE = new VillageCard();
	public static final Card WITCH = new WitchCard();
	public static final Card WOODCUTTER = new WoodcutterCard();
	
	// Intrigue
	public static final Card GREAT_HALL = new GreatHallCard();
	public static final Card HAREM = new HaremCard();
	
	// Seaside
	public static final Card BAZAAR = new BazaarCard();
}
