package com.adc.dataspark.util;

import static java.lang.Integer.parseInt;

import java.util.ArrayList;
import java.util.List;

public class MergeSortUtil {

	public <T> List<T> mergeSort(List<T> data) {
		if (data.size() <= 1) {
			return data;
		}
		List<T> right = new ArrayList<T>();
		List<T> left = new ArrayList<T>();

		int middle = data.size() / 2;
		for (T num : data.subList(0, middle)) {
			left.add(num);
		}
		for (T num : data.subList(middle, data.size())) {
			right.add(num);
		}
		left = mergeSort(left);
		right = mergeSort(right);

		return merge(left, right);
	}

	private <T> List<T> merge(List<T> left, List<T> right) {
		List<T> list = new ArrayList<T>();
		while (left.size() > 0 || right.size() > 0) {
			if (left.size() > 0 && right.size() > 0) {
				boolean bool = false;
				try {
					bool = parseInt(left.get(0).toString()) <= parseInt(right.get(0).toString());
				} catch (NumberFormatException e) {
					if (left.get(0).toString().compareTo(right.get(0).toString()) <= 0)
						bool = true;
				}
				if (bool) {
					list.add(left.get(0));
					if (left.size() > 1)
						left = left.subList(1, left.size());
					else
						left = new ArrayList<T>();
				} else {
					list.add(right.get(0));
					if (right.size() > 1)
						right = right.subList(1, right.size());
					else
						right = new ArrayList<T>();
				}
			} else if (left.size() > 0) {
				list.add(left.get(0));
				if (left.size() > 1)
					left = left.subList(1, left.size());
				else
					left = new ArrayList<T>();
			} else if (right.size() > 0) {
				list.add(right.get(0));
				if (right.size() > 1)
					right = right.subList(1, right.size());
				else
					right = new ArrayList<T>();
			}
		}
		return list;
	}
}