package me.rexysaur.void_.Client.util;

import java.util.ArrayList;
import java.util.List;

public class Auction {
	public class AC {
	    public String uuid;
	    public String auctioneer;
	    public String profile_id;
	    public List<String> coop;
	    public long start;
	    public long end;
	    public String item_name;
	    public String item_lore;
	    public String extra;
	    public String category;
	    public String tier;
	    public int starting_bid;
	    public String item_bytes;
	    public boolean claimed;
	    public List<Object> claimed_bidders;
	    public int highest_bid_amount;
	    public int profit;
	    public List<Object> bids;
	}

	public class Root{
	    public boolean success;
	    public int page;
	    public int totalPages;
	    public int totalAuctions;
	    public long lastUpdated;
	    public List<AC> auctions;
	}
}
