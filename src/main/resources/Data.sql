-- Insert Waste Categories
INSERT INTO waste_category (name, description) VALUES
    ('Plastic', 'All types of plastic materials including bottles, containers, and packaging'),
    ('Paper', 'Paper products including cardboard, newspapers, and magazines'),
    ('Glass', 'Glass bottles, jars, and other glass containers'),
    ('Metal', 'Metal cans, aluminum foil, and other metal items'),
    ('Organic', 'Food waste, yard trimmings, and other biodegradable materials');

-- Insert Waste Disposal Methods
INSERT INTO waste_disposal_entity (method_name, description, waste_category_id, environmental_impact, safety_precautions) VALUES
    ('Recycling Center Dropoff', 'Take clean plastics to local recycling center', 1, 'Reduces landfill waste', 'Clean items before recycling'),
    ('Curbside Recycling', 'Place in blue recycling bin', 1, 'Reduces waste and energy usage', 'Follow local sorting guidelines'),
    ('Paper Recycling', 'Collect and bundle paper products', 2, 'Saves trees and reduces landfill use', 'Remove any plastic or metal attachments'),
    ('Glass Collection', 'Separate by color and take to collection point', 3, 'Highly recyclable material', 'Handle with care, wear gloves'),
    ('Composting', 'Process organic waste into fertilizer', 5, 'Creates natural fertilizer', 'Maintain proper balance of materials');

-- Insert Recycling Tips
INSERT INTO recycling_tip (process_name, description, waste_category_id, benefits_description, processing_steps, resource_savings) VALUES
    ('Plastic Bottle Recycling', 'How to properly recycle plastic bottles', 1, 'Reduces oil consumption and landfill space', 'Clean, remove cap, crush, bin', 'Saves 2L of water per bottle'),
    ('Paper Sorting', 'Effective paper waste sorting', 2, 'Saves trees and reduces pollution', 'Sort by type, remove staples, bundle', 'One ton saves 17 trees'),
    ('Glass Recycling', 'Proper glass recycling process', 3, 'Infinitely recyclable material', 'Sort by color, clean, remove lids', 'Reduces mining impact'),
    ('Metal Can Prep', 'Preparing metal cans for recycling', 4, 'Saves mining resources', 'Clean, crush, remove labels', 'Saves 95% energy vs new production'),
    ('Home Composting', 'Creating compost from kitchen waste', 5, 'Creates rich soil amendment', 'Layer greens and browns, turn regularly', 'Reduces methane emissions');
