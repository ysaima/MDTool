package com.youbenzi.mdtool.markdown.builder;

import java.util.List;

import com.youbenzi.mdtool.markdown.Analyzer;
import com.youbenzi.mdtool.markdown.BlockType;
import com.youbenzi.mdtool.markdown.MDToken;
import com.youbenzi.mdtool.markdown.bean.Block;
import com.youbenzi.mdtool.markdown.bean.ValuePart;

public class HeaderBuilder implements BlockBuilder{

	private String content;
	public HeaderBuilder(String content){
		this.content = content;
	}
	
	public Block bulid() {
		content = content.trim();
		int i = content.lastIndexOf(MDToken.HEADLINE);
		content = content.substring(i+1).trim();
		return bulid(i);
	}
	
	public Block bulid(int level) {
		Block block = new Block();
		List<ValuePart> list = Analyzer.analyzeLineText(content);
		block.setType(BlockType.HEADLINE);
		block.setValueParts(list);
		block.setLevel(level + 1);
		return block;
	}

	public boolean isRightType() {
		return content.startsWith(MDToken.HEADLINE);
	}

	public static int isRightType(String nextLineStr){
		if(!nextLineStr.startsWith("-") && !nextLineStr.startsWith("=")){
			return 0;
		}
		String tmpS = nextLineStr.replaceAll("-", "").trim();
		if(tmpS.length()==0){
			return 2;
		}
		tmpS = nextLineStr.replaceAll("=", "").trim();
		if(tmpS.length()==0){
			return 1;
		}
		return 0;
	}
	
}
